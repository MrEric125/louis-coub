package com.concurrent.designpation.immutableobject;

/**
 * @author John·Louis
 * @date created on 2020/2/2
 * description:
 * 模式角色:{@link Manipulator}
 */
public class OMCAgent implements Runnable {


    @Override
    public void run() {
        boolean isTableModificationMsg = false;
        String updatedTableName = null;
        while (true) {
//省略某些代码
            if (isTableModificationMsg) {
                if ("MMSCInfo".equals(updatedTableName)) {
                    MMSCRouter.setInstance(new MMSCRouter());

                }
            }
//            省略某些代码
        }
    }
}
