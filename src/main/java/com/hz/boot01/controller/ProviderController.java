package com.hz.boot01.controller;

import com.hz.boot01.pojo.Provider;
import com.hz.boot01.service.ProviderService;
import com.hz.boot01.utils.JsonMassage;
import com.hz.boot01.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.security.x509.OIDMap;

import javax.xml.transform.Result;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/testRedis")
    @ResponseBody
    public String testRedis(){

       // redisTemplate.opsForValue().set("name","Tom",60,TimeUnit.SECONDS);
        //System.out.println(redisTemplate.opsForValue().get("name"));

        //存储一个对象（此类必须先序列化实现接口Serializable）
        /*Provider provider = providerService.findProviderById(1);
        RedisSerializer rs = new StringRedisSerializer();
        redisTemplate.setStringSerializer(rs);
        ValueOperations vo = redisTemplate.opsForValue();
        vo.set("provider",provider,60,TimeUnit.SECONDS);
        Provider setProvider = (Provider) redisTemplate.opsForValue().get("provider");
        System.out.println(setProvider.toString());*/

        return "成功";
    }


    @RequestMapping(value = "/saveProvider",method = RequestMethod.POST)
    @ResponseBody
    public ResultJson saveProvider(Provider provider){
        int i = providerService.saveProvider(provider);
        return new ResultJson(i);
    }

    /**
     * 分页查询  多条件
     * @param page  当前页
     * @param limit1 每页显示条数
     * @return
     */
    @RequestMapping(value = "/findProviderList",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<List<Provider>> findProviderList(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "limit1",defaultValue = "10") Integer limit1,
             String proName,
             String proDesc
            ){
        List<Provider> list = providerService.findProviderList(page,limit1,proName,proDesc);
        Integer count = providerService.findProviderListCount(proName,proDesc);

        JsonMassage<List<Provider>> jsonMassage = new JsonMassage<List<Provider>>();
        jsonMassage.setCode(0);
        jsonMassage.setMsg("请求成功");
        jsonMassage.setCount(count);
        jsonMassage.setData(list);
        return jsonMassage;
    }
    @RequestMapping("/findProviderById/{id}")
    public String findProviderById(@PathVariable("id") Integer id, Model model){

        model.addAttribute("pro",providerService.findProviderById(id));
        return "provider_edit";
    }


    @RequestMapping(value = "/deleteProviderById",method = RequestMethod.POST)
    @ResponseBody
    public ResultJson deleteProviderById(Integer id){
        int i = providerService.deleteProviderById(id);
        return new ResultJson(i);
    }

    @RequestMapping(value = "/updateProvider",method = RequestMethod.POST)
    @ResponseBody
    public ResultJson updateProvider(Provider provider){
        int i = providerService.updateProvider(provider);
        return new ResultJson(i);
    }

}
