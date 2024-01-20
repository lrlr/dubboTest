package provider.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaskTaskBaseVo {
    //任务名称
    public String taskName;

    //任务备注
    public String taskRemark;
    //任务类型 1：手动领取 2：自动领取
    public int taskType;
    //任务编码
    public String taskCode;
    //任务总进度
    public int taskOverProcess;
    // 任务跳转链接
    public String taskUrl;

    public MaskTaskBaseVo(){

    }
}
