package com.rozarltd.junit;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class RetryJUnitTestRunner extends BlockJUnit4ClassRunner {

    public RetryJUnitTestRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
        public Statement methodInvoker(final FrameworkMethod method,
                                       Object test) {
            final Statement singleTryStatement = super.methodInvoker(method, test);
            return new Statement() {

                public void evaluate() throws Throwable {
                    Throwable failureReason = null;

                    Retry retry = method.getAnnotation(Retry.class);
                    if (retry == null) {

                        // Do a single test run attempt.
                        singleTryStatement.evaluate();
                    }
                    else {
                        final int numRetries = retry.value();

                        for (int i = 0; i < numRetries; ++i) {
                            try {
                                // Do a single test run attempt.
                                singleTryStatement.evaluate();

                                // Attempt succeeded, stop evaluation here.
                                return;
                            } catch (Throwable t) {

                                // Attempt failed, store the reason.
                                failureReason = t;
                            }
                        }

                        // All attempts failed.
                        throw failureReason;
                    }
               }
           };
       }
}
