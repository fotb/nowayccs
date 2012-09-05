package com.ccs.util;

/**
 */
public class SystemException extends BaseException {

        /**
         * @param :
         * @return :
         */
        public SystemException(){
                super("Error occurred in system.");
        }

        /**
         * @return :
         */
        public SystemException(String message){
                super(message);
        }

        /**
         * @param :
         * @return :
         */
        public SystemException(String message,Throwable nested){
                super(message,nested);
        }

        /**
         * @param :
         * @return :
         */
        public SystemException(Throwable nested){
                super(nested);
        }
}
