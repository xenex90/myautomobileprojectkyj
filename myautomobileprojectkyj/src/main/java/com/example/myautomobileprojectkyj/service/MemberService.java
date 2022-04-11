package com.example.myautomobileprojectkyj.service;

import com.example.myautomobileprojectkyj.vo.MemberVo;


public interface MemberService{

    void joinMember(MemberVo memberVo) throws Exception;

    MemberVo loginMember(String id, String password) throws Exception;

    int isMember(MemberVo memberVo) throws Exception;

    MemberVo selectMember(MemberVo memberVo) throws Exception;
}
