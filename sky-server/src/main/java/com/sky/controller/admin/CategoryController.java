package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类接口")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @ApiOperation("新增分类")
    @PostMapping
    public Result save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类：{}",categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    @ApiOperation("根据id删除分类")
    @DeleteMapping
    public Result deleteById(Long id){
        log.info("根据id删除分类：{}",id);
        categoryService.deleteById(id);
        return Result.success();
    }

    @ApiOperation("修改分类")
    @PutMapping
    public Result update(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类：{}",categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }

    @ApiOperation("启用禁用分类")
    @PostMapping("/status/{status}")
    public Result updateStatus(Long id, @PathVariable Integer status){
        log.info("启用分类：{}",id);
        categoryService.updateStatus(status,id);
        return Result.success();
    }

    @ApiOperation("查询分类")
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type){
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }

    @ApiOperation("分页查询分类")
    @GetMapping("/page")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        return Result.success(categoryService.pageQuery(categoryPageQueryDTO));
    }
}
