package com.ccs.util;

/**
 * @Functionality: 应用级异常基类
 */
public class ApplicationException extends BaseException {

        /**
         * @Functionality: 构造函数
         * @param :
         * @return :
         */
        public ApplicationException(){
                super("Error occurred in application.");
        }

        /**
         * @Functionality: 构造函数
         * @param : 异常消息
         * @return :
         */
        public ApplicationException(String message){
                super(message);
        }


        /**
         * @Functionality: 构造函数
         * @param : 异常消息
         * @return :
         */
        public ApplicationException(String message,Throwable nested){
                super(message,nested);
        }

        /**
         * @Functionality: 构造函数
         * @param :
         * @return :
         */
        public ApplicationException(Throwable nested){
                super(nested);
        }
}
