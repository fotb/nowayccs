package com.ccs.util;

/**
 * @Functionality: ϵͳ���쳣����
 */
public class SystemException extends BaseException {

        /**
         * @Functionality: ���캯��
         * @param :
         * @return :
         */
        public SystemException(){
                super("Error occurred in system.");
        }

        /**
         * @Functionality: ���캯��
         * @param : �쳣��Ϣ
         * @return :
         */
        public SystemException(String message){
                super(message);
        }

        /**
         * @Functionality: ���캯��
         * @param :
         * @return :
         */
        public SystemException(String message,Throwable nested){
                super(message,nested);
        }

        /**
         * @Functionality: ���캯��
         * @param :
         * @return :
         */
        public SystemException(Throwable nested){
                super(nested);
        }
}
