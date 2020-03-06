package com.concurrent.designpation.immutableobject;

/**
 * @author John·Louis
 * @date created on 2020/2/2
 * description:
 * 彩信中心信息
 * 模式角色：{@link ImmutableObject}
 */
public class MMSCInfo {

    private final String deviceID;

    private final String URL;

    private final int maxAttachmentSizeInBytes;

    public MMSCInfo(String deviceID, String URL, int maxAttachmentSizeInBytes) {
        this.deviceID = deviceID;
        this.URL = URL;
        this.maxAttachmentSizeInBytes = maxAttachmentSizeInBytes;
    }

    public MMSCInfo(MMSCInfo prototype) {
        this.deviceID = prototype.deviceID;
        this.URL = prototype.URL;
        this.maxAttachmentSizeInBytes = prototype.maxAttachmentSizeInBytes;

    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getURL() {
        return URL;
    }

    public int getMaxAttachmentSizeInBytes() {
        return maxAttachmentSizeInBytes;
    }
}
