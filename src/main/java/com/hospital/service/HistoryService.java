package com.hospital.service;

import com.hospital.entity.History;
import com.hospital.entity.Patient;
import com.hospital.entity.Doctor;
import com.hospital.entity.Registration;
import com.hospital.mapper.DoctorMapper;
import com.hospital.mapper.HistoryMapper;
import com.hospital.mapper.RegistrationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class HistoryService {
    @Resource
    private HistoryMapper historyMapper;
    @Resource
    private RegistrationMapper registrationMapper;
    @Resource
    private DoctorMapper doctorMapper;

    // 显示病历所属科室
    public String showdepartmentofhis(Integer doctorId) {
        return historyMapper.showdept(doctorId);
    }

    // 根据患者身份证号初始化病历, 并返回患者相关信息
    public Patient originatebypid(String pIdentificationNum){
        Integer id = historyMapper.getidBypid(pIdentificationNum);
        Patient pinfo = historyMapper.getPatientinfo(id);      // 获得患者其他相关信息
        return pinfo;
    }

    // 返回患者相关信息
    public Patient patientInfo(Integer patientId){
        Patient pinfo = historyMapper.getPatientinfo(patientId);      // 获得患者其他相关信息
        return pinfo;
    }

    // 编辑病历
    public void editcasehis(History history) {
        Patient pinfo = historyMapper.getPatientinfo(history.getPatientId());
        history.setpIdentificationNum(pinfo.getpIdentificationNum());
        historyMapper.edit(history);
    }

    // 查看历史病历
    public List<History> showAllHisByPid(String pIdentificationNum) {
        List<History> history = historyMapper.showAllHisByPid(pIdentificationNum);
        return history;
    }



    //获取当前叫号叫到的患者的信息
    public Patient showpatientInfo(Integer doctorId) {
        Doctor doctor = doctorMapper.selectbyid(doctorId);
        if(doctor.getPatientId()==null){//还未就诊，开始叫号
            endregistration(doctorId);
        }
        Patient patient = historyMapper.getPatientinfo(doctor.getPatientId());
        return  patient;
    }

    public List<History> showAllHis(){
        List<History> histories = historyMapper.showAllHis();
        return histories;
    }

    public History showHis(Integer historyId){
        History history = historyMapper.selectById(historyId);
        return history;
    }

    public boolean checkRNum(String rNum, List<String> rNums){
        for(int i = 0;i<rNums.size();i++){
            if(rNum.equals(rNums.get(i)))
                return true;
        }
        return false;
    }

    //结束当前就诊病人,开始叫下一号
    public void endregistration(Integer doctorId){
        Doctor doctor = doctorMapper.selectbyid(doctorId);
        registrationMapper.updateValid(doctor.getPatientId());
        List<String> rNums = new LinkedList<>();
        if(doctor.getdTitle().equals("专家"))
            rNums = registrationMapper.TodayRNumz(doctor.getDoctorId());
        else
            rNums = registrationMapper.TodayRNum(doctor.getdOffice());
        List<Long> vNums=new LinkedList<>();//vip
        List<Long> nNums=new LinkedList<>();//普通
        for (int i=0;i<rNums.size();i++){
            if(rNums.get(i).contains("v")){
                Long l = Long.parseLong((rNums.get(i).replace("v","")));
                vNums.add(l);
            }
            else{
                Long l = Long.parseLong(rNums.get(i));
                nNums.add(l);
            }
        }
        //排序
        if(vNums!=null)
            Collections.sort(vNums);
        if (nNums!=null)
            Collections.sort(nNums);
        Registration registration = new Registration();
        registration.setDepartment(doctor.getdOffice());
        Integer patientId = null;

        if (doctor.getdTitle().equals("专家")&& doctor.getPatientId()==null) {//专家号
            if (vNums.size() > 0 )
                registration.setrNum("v" + String.format("%06d", vNums.get(0)));
            else
                registration.setrNum(String.format("%06d", nNums.get(0)));
            registration.setDoctorId(doctorId);
            System.out.println("hhh"+registration.getrNum());
            patientId = registrationMapper.selectByrNum(registration);
        } else if(doctor.getdTitle().equals("专家")&& doctor.getPatientId()!=null){
            String rNum = registrationMapper.selectPre(doctor.getPatientId());
            System.out.println(nNums.get(0)+"排队号");
            if (vNums.size() > 0 && !rNum.contains("v"))
                registration.setrNum("v" + String.format("%06d", vNums.get(0)));
            else
                registration.setrNum(String.format("%06d", nNums.get(0)));
            registration.setDoctorId(doctorId);
            patientId = registrationMapper.selectByrNum(registration);
            System.out.println("你觉得"+patientId);

        }else {//普通的医生需要分诊，查看挂了号中第一个没有被科室其他医生看的病人
            //List
            List<Integer> allPID = doctorMapper.selectbydepartment(doctor.getdOffice());
            List<String> allRNUM = new LinkedList<>();
            if(allPID.get(0)!=null){//如果有人挂
                for (int i = 0; i < allPID.size(); i++) {
                    allRNUM.add(registrationMapper.selectById(allPID.get(i)).getrNum());
                }
            }
            for (int i = 0; i < rNums.size(); i++) {
                if (i < vNums.size()) {
                    if (!checkRNum("v" +String.format("%06d", vNums.get(i)), allRNUM)) {
                        registration.setrNum("v" +String.format("%06d", vNums.get(i)));
                        break;
                    }
                } else if (i < nNums.size()) {
                    if (i < vNums.size()) {
                        if (!checkRNum(String.format("%06d", nNums.get(0)), allRNUM)) {
                            registration.setrNum(String.format("%06d", nNums.get(0)));
                            break;
                        }
                    }
                }
            }

            patientId = registrationMapper.selectPatient(registration);
        }
        System.out.println("hhh"+patientId);
        System.out.println(registrationMapper.selectById(doctor.getPatientId())+"结束看病");
        if (doctor.getPatientId() == null) {
            //更新医生状态
            doctorMapper.updatepId(patientId, doctorId);
        } else if (registrationMapper.selectById(doctor.getPatientId()) == null && patientId!=null) {//看完了上一个病人的状态
            //更新医生状态
            doctorMapper.updatepId(patientId, doctorId);
        }
    }
}
