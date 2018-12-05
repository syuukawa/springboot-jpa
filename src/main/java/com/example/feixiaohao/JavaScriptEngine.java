package com.example.feixiaohao;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;

public class JavaScriptEngine {
    private static JavaScriptEngine instance = null;
    private ScriptEngine engine;
    /**
     * 调用js函数所需
     */
    private static final String DECODE_KEY = "abc";

    /**
     * 返回单例 * * @return
     */
    public static JavaScriptEngine getInstance() {
        if (instance == null) instance = new JavaScriptEngine();
        return instance;
    }

    /**
     * 无参构造器 初始化需要的js引擎 *
     */
    private JavaScriptEngine() {
        try {
            //调用Java8 nashorn 运行JavaScript脚本
            this.engine = new ScriptEngineManager().getEngineByName("nashorn");
            //读取文件对象
            Resource aesJs = new ClassPathResource("js/aes.js");
            Resource modeEcbJs = new ClassPathResource("js/ecb.js");
            Resource rnavJs = new ClassPathResource("js/nav.js");
            //执行脚本
            this.engine.eval(new FileReader(aesJs.getFile()));
            this.engine.eval(new FileReader(modeEcbJs.getFile()));
            this.engine.eval(new FileReader(rnavJs.getFile()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("js脚本初始化失败");
        }
    }

    /**
     * 调用JavaScript的解密函数 * * @param word * @return * @throws NoSuchMethodException * @throws ScriptException
     */
    public String decodeData(String word) throws NoSuchMethodException, ScriptException {
        if (word == null || word == "") {
            throw new RuntimeException();
        }
        Invocable invocable = (Invocable) engine;
        //Decrypt是js函数名, word, DECODE_KEY是参数
        return (String) invocable.invokeFunction("Decrypt", word, DECODE_KEY);
    }
}
