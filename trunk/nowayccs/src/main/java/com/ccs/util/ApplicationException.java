package com.ccs.util;

/**
 * @Functionality: Ӧ�ü��쳣����
 */
public class ApplicationException extends BaseException {

        /**
         * @Functionality: ���캯��
         * @param :
         * @return :
         */
        public ApplicationException(){
                super("Error occurred in application.");
        }

        /**
         * @Functionality: ���캯��
         * @param : �쳣��Ϣ
         * @return :
         */
        public ApplicationException(String message){
                super(message);
        }


        /**
         * @Functionality: ���캯��
         * @param : �쳣��Ϣ
         * @return :
         */
        public ApplicationException(String message,Throwable nested){
                super(message,nested);
        }

        /**
         * @Functionality: ���캯��
         * @param :
         * @return :
         */
        public ApplicationException(Throwable nested){
                super(nested);
        }
}
