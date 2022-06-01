package com.example.myautomobileprojectkyj.service;

import com.example.myautomobileprojectkyj.vo.MemberVo;

import java.util.HashMap;


public interface MemberService{

    void joinMember(MemberVo memberVo) throws Exception;

    MemberVo loginMember(String id, String password) throws Exception;

    int isMember(MemberVo memberVo) throws Exception;

    String selectMember(HashMap<String,String> map) throws Exception;

    int countOfIdMember(HashMap<String, String> map) throws Exception;
}
