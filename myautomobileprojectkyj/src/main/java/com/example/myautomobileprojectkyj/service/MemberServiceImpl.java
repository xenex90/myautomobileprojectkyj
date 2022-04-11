package com.example.myautomobileprojectkyj.service;

import com.example.myautomobileprojectkyj.mapper.MemberMapper;
import com.example.myautomobileprojectkyj.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MemberServiceImpl implements MemberService{
    private MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public void joinMember(MemberVo memberVo) {
        memberMapper.insertMember(memberVo);
    }

    @Override
    public MemberVo loginMember(String id, String password){
        HashMap map = new HashMap<String, String>();
        map.put("id",id);
        map.put("password",password);

       return memberMapper.selectMember(map);
    }

    public int isMember(MemberVo memberVo) {
        HashMap map = new HashMap<String, String>();
        map.put("id",memberVo.getId());
        map.put("password",memberVo.getPassword());
        return memberMapper.countMember(map);
    }

    public MemberVo selectMember(MemberVo memberVo) {
        HashMap map = new HashMap<String, String>();
        map.put("",memberVo.getName());
        map.put("",memberVo.getPhonenum());
        map.put("",memberVo.getEmail());
        map.put("",memberVo.getDomain());

        return memberMapper.getIdOfMember(map);
    }
}
