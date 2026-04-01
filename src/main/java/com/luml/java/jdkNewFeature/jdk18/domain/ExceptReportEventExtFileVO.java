package com.luml.java.jdkNewFeature.jdk18.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author luml
 * @description 异常事件上报附近内容
 * @date 2025/10/29
 */
@Data
@AllArgsConstructor
public class ExceptReportEventExtFileVO {

    private String url;

    /** 上传资源的类型 1.文字 2.图片 3.语音 4.视频 */
   // @ApiModelProperty("文件类型")
    private Integer urlType;

   // @ApiModelProperty("文件排序")
    private Integer order;

   // @ApiModelProperty("文件位置: 主要是铅封照片位置")
    private String position;

    //@ApiModelProperty("文件审核意见")
    private String remark;

    /**
     * 附件审核意见：1通过 2 驳回
     */
    //@ApiModelProperty("文件审核状态")
    private Integer audit;

    //@ApiModelProperty("铅封号")
    private String sealNo;
}
