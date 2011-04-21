package com.ccs.util;

/**
 * @Functionality: 系统级异常基类
 */
public class SystemException extends BaseException {

        /**
         * @Functionality: 构造函数
         * @param :
         * @return :
         */
        public SystemException(){
                super("Error occurred in system.");
        }

        /**
         * @Functionality: 构造函数
         * @param : 异常消息
         * @return :
         */
        public SystemException(String message){
                super(message);
        }

        /**
         * @Functionality: 构造函数
         * @param :
         * @return :
         */
        public SystemException(String message,Throwable nested){
                super(message,nested);
        }

        /**
         * @Functionality: 构造函数
         * @param :
         * @return :
         */
        public SystemException(Throwable nested){
                super(nested);
        }
}
