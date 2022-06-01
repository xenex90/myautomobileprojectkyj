package com.example.myautomobileprojectkyj.mapper;

import com.example.myautomobileprojectkyj.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;


@Mapper
public interface MemberMapper{

   void insertMember(MemberVo memberVo);

   MemberVo selectMember(HashMap map);

   int countMember(HashMap map);

   String getIdOfMember(HashMap map);

   int countOfIdMember(HashMap map);
}
