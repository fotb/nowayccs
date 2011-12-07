package com.ccs.util;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 */
public class BaseException extends Exception {

        protected Throwable nested;

        /**
         * @param :
         * @return :
         */
        public BaseException(){
                super();
        }

        /**
         * @return :
         */
        public BaseException(String message){
                super(message);
        }

        /**
         * @param :
         * @return :
         */
        public BaseException(String message,Throwable nested){
                super(message);
                this.nested = nested;
        }

        /**
         * @param :
         * @return :
         */
        public BaseException(Throwable nested){
                super();
                this.nested = nested;
        }

        /**
         * @Functionality: Returns the detail message, including the message from the nested exception if there is one.
         * @param:
         * @return:
         */
        public String getMessage() {
/*	  if (nested != null){
                return super.getMessage() + " (" + this.getMessage() + ")";
          }
          else
*/		return super.getMessage();
        }

        /**
         * @Functionality: Returns the detail message, NOT including the message from the nested exception.
         * @param:
         * @return:
         */
        public String getNonNestedMessage() {
          return super.getMessage();
        }

        /**
         * @Functionality:Returns the nested exception if there is one, null if there is not.
         * @param
         * @return:
         */
        public Throwable getNested() {
          if (nested == null)
                return this;

          return nested;
        }

        /**
         * @Functionality: Prints the composite message to System.err.
         * @param:
         * @return:
         */
        public void printStackTrace() {
                super.printStackTrace();
                if (nested != null) nested.printStackTrace();
        }

        /**
         * @Functionality: Prints the composite message and the embedded stack trace to the specified stream ps.
         * @param: ps
         * @return:
         */
        public void printStackTrace(PrintStream ps) {
          super.printStackTrace(ps);
          if (nested != null) nested.printStackTrace(ps);
        }

        /**
         * @Functionality: Prints the composite message and the embedded stack trace to the specified print writer pw.
         * @param: pw
         * @return:
         */
        public void printStackTrace(PrintWriter pw) {
                super.printStackTrace(pw);
                if (nested != null) nested.printStackTrace(pw);
        }
}
