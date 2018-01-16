package com.williamssonoma.automationCore.util.verificationServices;

import com.williamssonoma.automationCore.util.StringMatcher;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

public class Verifications {
    protected List<CheckpointResultBean> checkPointResults;
    protected StringBuffer verificationErrors = new StringBuffer();

    public  Verifications() {
    }

    public static Boolean verifyTrue(boolean condition, String message) {
        try{
            Assert.assertTrue(condition, message);
            Reporter.log(message);
            return true;
        }catch(AssertionError e){
            addToErrorBuffer(e);
            return false;
        }
    }

     public Boolean verifyTrue(boolean b, String failMessage, String successMessage) {
     try{
         assertTrue(b, failMessage, successMessage);
         return true;
        }catch(AssertionError e){
          addToErrorBuffer(e);
          return false;
        }
     }

     public void assertTrue(boolean b, String failMsg, String successMsg) {
         if (!b) {
            addAssertionLog(failMsg, MessageTypes.Fail);

            throw new AssertionError(failMsg);
        }
        addAssertionLog(successMsg, MessageTypes.Pass);
    }

    static public void verifyFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(condition, message);
        } catch (AssertionError e) {
            addToErrorBuffer(e);
        }
    }

     static public void verifyEquals(Object actual, Object expected, String message) {
       try{
       Assert.assertEquals(actual, expected, message);
       }catch(AssertionError e){
         addToErrorBuffer(e);
       }
    }

     public static void verifyNotEquals(Object actual1, Object actual2, String message) {
         try {
             Assert.assertNotEquals(actual1, actual2, message);
         } catch (AssertionError e) {
             addToErrorBuffer(e);
         }
     }
     private static void addToErrorBuffer(AssertionError e) {
        try {
            VerificationError verificationError = new VerificationError(e.getMessage());
            verificationError.setStackTrace(e.getStackTrace());
            TestMethodErrorBuffer.get().add(verificationError);
           } catch (NullPointerException ex) {
          throw new RuntimeException("TestNG Listener for verify statements" + TestMethodListener.class.getName());
        }
    }


    public void addAssertionLog(String msg, MessageTypes type) {
        CheckpointResultBean bean = new CheckpointResultBean();
        bean.setMessage(msg);
        bean.setType(type);
        addCheckpoint(bean);

    }

    private void addCheckpoint(CheckpointResultBean bean) {
        int checkPoints = checkPointResults.size();
        CheckpointResultBean lastCheckpoint = checkPoints > 1 ? checkPointResults.get(checkPoints - 1) : null;

        List<CheckpointResultBean> parent = MessageTypes.TestStep.name().equalsIgnoreCase(bean.getType())
                || (lastCheckpoint == null) || !MessageTypes.TestStep.name().equalsIgnoreCase(lastCheckpoint.getType())
                ? checkPointResults : lastCheckpoint.getSubCheckPoints();

        CheckpointResultBean prevCheckpointResultBean = !parent.isEmpty() ? parent.get(parent.size() - 1) : null;

        if ((prevCheckpointResultBean == null) || !prevCheckpointResultBean.equals(bean)) {
            parent.add(bean);
            if ((lastCheckpoint != null) && MessageTypes.TestStep.name().equalsIgnoreCase(lastCheckpoint.getType())) {
                lastCheckpoint.setType(hasFailure(lastCheckpoint.getSubCheckPoints()) ? MessageTypes.TestStepFail
                        : MessageTypes.TestStepPass);
            }

        }

    }

    private boolean hasFailure(List<CheckpointResultBean> subSteps) {
        for (CheckpointResultBean subStep : subSteps) {
            if (StringMatcher.containsIgnoringCase("fail").match(subStep.getType())) {
                return true;
            }
        }
        return false;
    }
}