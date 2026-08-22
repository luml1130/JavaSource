package com.luml.source.java.jdkNewFeature.jdk8.domain;

import lombok.Data;

import java.util.List;

/**
 * @author luml
 * @description
 * @date 2026/3/31
 */
@Data
public class EventReportInfoPO {
    private List<ExceptReportEventExtFileVO> extFileList;
}
