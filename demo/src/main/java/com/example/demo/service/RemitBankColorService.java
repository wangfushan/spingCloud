package com.example.demo.service;

import com.example.demo.entity.RemitBankColor;
import com.example.demo.mapper.RemitBankColorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RemitBankColorService {
    @Autowired
    private RemitBankColorMapper remitBankColorMapper;

    public List<RemitBankColor> listRemit(Map<String, Object> map) {

        return remitBankColorMapper.listBankColor(map);
    }


    public Map<String, Object> list(String typeCode) {
        Example example = new Example(RemitBankColor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeCode", typeCode);
        List<RemitBankColor> list = remitBankColorMapper.selectByExample(example);
        Map<String, Object> map = new HashMap<>();
        if (list.isEmpty()) {
            map.put("a", null);
        } else {
            map.put("a", list);
        }
        return map;
    }



}
