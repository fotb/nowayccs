package com.ccs.util;

/**
 * @Functionality: Ӧ�ü��쳣����
 */
public class AppException extends BaseException {

        /**
         * @Functionality: ���캯��
         * @param :
         * @return :
         */
        public AppException(){
                super("Error occurred in application.");
        }

        /**
         * @Functionality: ���캯��
         * @param : �쳣��Ϣ
         * @return :
         */
        public AppException(String message){
                super(message);
        }


        /**
         * @Functionality: ���캯��
         * @param : �쳣��Ϣ
         * @return :
         */
        public AppException(String message,Throwable nested){
                super(message,nested);
        }

        /**
         * @Functionality: ���캯��
         * @param :
         * @return :
         */
        public AppException(Throwable nested){
                super(nested);
        }
}
