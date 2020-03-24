package com.example.demo.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
@Setter
@Getter
@ToString
public class CsvImportBo {
    private String methodName;
    private CSVRecord csvRecord;
    private Boolean success;
    private Exception exception;

    public CsvImportBo() {
        this(2);
    }

    public CsvImportBo(int i) {
        methodName = Thread.currentThread().getStackTrace()[i].getMethodName();
        exception = null;
    }

    public static CsvImportBo getSuccessInstance(CSVRecord csvRecord) {
        CsvImportBo csvImportBo = new CsvImportBo(3);
        csvImportBo.setCsvRecord(csvRecord);
        csvImportBo.setSuccess(true);
        return csvImportBo;
    }

    public static CsvImportBo getFailInstance(CSVRecord csvRecord, Exception exception) {
        CsvImportBo csvImportBo = new CsvImportBo(3);
        csvImportBo.setCsvRecord(csvRecord);
        csvImportBo.setSuccess(false);
        csvImportBo.setException(exception);
        return csvImportBo;
    }

}
