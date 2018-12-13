package cn.com.jonpad.api.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 统一返回对象
 * @author Jon Chan
 * @date 2018/12/12 23:25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value="统一返回对象", description="")
public class Result<T> {
    T data;

    /**
     * 获取元素是否为空
     * @return
     */
    public boolean isNull(){
        if(data == null){
            return true;
        }else{
            return false;
        }
    }

    public static Result getInstance(Object data){
        return new Result().setData(data);
    }
}
