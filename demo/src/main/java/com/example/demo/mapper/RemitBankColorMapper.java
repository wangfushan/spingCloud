package com.example.demo.mapper;

import com.example.demo.common.util.MyMapper;
import com.example.demo.entity.RemitBankColor;

import java.util.List;
import java.util.Map;
public interface RemitBankColorMapper  extends MyMapper<RemitBankColor> {
    /**
     * 查询城市
     * @param map
     * @return
     */
    List<RemitBankColor> listBankColor(Map<String ,Object> map);

}
