package provider.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MaskActivityTaskBaseVo {
    public  String activityCode="";
    public List<MaskTaskBaseVo> maskTaskBaseVoList;
    public Long bookId;

    public MaskActivityTaskBaseVo(){

    }
}
