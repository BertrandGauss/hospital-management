package com.hospital.mapper;

import com.hospital.entity.Doctor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorMapper {
    void add(Doctor doctor);         // 注册，增加医生信息

    String selectpwbydphone(String dPhone);  // 根据医生身份证号查找密码

    String selectpwbyid(Integer doctorId); // 根据医生ID查找密码

    Doctor selectbyid(Integer doctorId);  // 根据医生ID查询医生所有信息

    Integer selectByIdentificationNum(String did); // 根据医生身份证号查看是否登录

    Integer selectBydPhone(String dPhone); // 根据医生身份证号查看是否登录

    Integer checkValidBydphone(String dPhone); // 根据医生手机号查看账号是否有效

    void update(Doctor doctor); // 更新医生信息

    void updatepw(@Param("doctorId") Integer doctor, @Param("dPassword") String dPassword);

    Integer selectbyName(@Param("department") String department, @Param("dName") String dName);

    void updatepId(@Param("patientId")Integer patientId,@Param("doctorId") Integer doctorId);

    List<Integer> selectbydepartment(String department);

    Doctor getDoctor(@Param("patientId")Integer patientId,@Param("department") String department);

    void deleteDoctorRegister(String dIdentificationNum );
}
