package com.catty.lepus.dto.testcase;

import com.catty.lepus.constants.BizConstant;
import com.catty.lepus.constants.SystemConstant;
import com.catty.lepus.dto.ParamValidate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @Description 用例 新建、复制使用的请求体
 * @Author catty
 * @Date 2022/11/15 16:39
 **/
@ApiModel(value = "列表 - 创建或者复制用例实体类", description = "请求类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCaseDto implements ParamValidate {

    /**
     * 必填 创建人邮箱前缀
     */
    @ApiModelProperty(value = "创建人邮箱前缀", example = "creator", required = true)
    private String creator;

    /**
     * 必填 业务线id
     */
    @ApiModelProperty(value = "业务线id", example = "productLineId", required = true)
    private Long productLineId;

    /**
     * 必填 用例种类 默认给0
     */
    @ApiModelProperty(value = "用例种类 默认给0", example = "0", required = true)
    private Integer caseType;

    /**
     * 必填 用例初始化内容 这里是前端传入的，本质上应该由后端自己写死
     */
    @ApiModelProperty(value = "用例种类 默认给0", example = "0", required = true)
    private String caseContent;

    /**
     * 必填 用例标题
     */
    @ApiModelProperty(value = "用例种类 默认给0", example = "0", required = true)
    private String title;

    /**
     * 必填 默认给0
     * 其实这里的channel意思就是渠道，用户在agileTc页面创建的就是1
     * 如果是其他自定义系统过来的，可以自定义
     */
    @ApiModelProperty(value = "渠道", example = "0", required = false)
    private Integer channel;

    /**
     * 必填 -- 但是表单不填，如果没有传默认给-1
     * 关联文件夹ids，现在无论done还是oe都默认给-1
     */
    @ApiModelProperty(value = "关联文件夹ids 默认给-1", example = "-1", required = true)
    private String bizId;

    /**
     * 非必填
     * 复制需要传id
     * 新建不需要传
     */
    @ApiModelProperty(value = "复制需要传id，新建不需要传", example = "0", required = false)
    private Long id;

    /**
     * 非必填 需求id
     */
    @ApiModelProperty(value = "requirementId", example = "requirementId", required = false)
    private String requirementId;

    /**
     * 非必填 描述
     */
    @ApiModelProperty(value = "description", example = "description", required = false)
    private String description;

    @Override
    public void validate() {
        // 复制操作才需要id
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("所复制的用例id非法");
        }

        // 文件夹判断
        if (StringUtils.isEmpty(bizId)) {
            throw new IllegalArgumentException("文件夹选择为空");
        } else {
            long count = Arrays.stream(bizId.split(SystemConstant.COMMA)).filter(BizConstant.ROOT_BIZ_ID::equals).count();
            if (count > 0) {
                throw new IllegalArgumentException("不可以在根文件夹下创建用例");
            }
        }

        if (StringUtils.isEmpty(creator)) {
            throw new IllegalArgumentException("创建人为空");
        }
        if (productLineId == null) {
            throw new IllegalArgumentException("业务线id为空");
        }
        if (StringUtils.isEmpty(title)) {
            throw new IllegalArgumentException("标题输入为空");
        }
        if (StringUtils.isEmpty(caseContent)) {
            throw new IllegalArgumentException("新建的用例内容为空");
        }
        if (caseType == null) {
            throw new IllegalArgumentException("用例种类为空");
        }
        if (channel == null) {
            throw new IllegalArgumentException("渠道为空");
        }

        // 如果没传，默认给空字符串，这样service层不需要特殊判断了
        if (StringUtils.isEmpty(requirementId)) {
            requirementId = SystemConstant.EMPTY_STR;
        }
        if (description == null) {
            description = SystemConstant.EMPTY_STR;
        }
    }
}
