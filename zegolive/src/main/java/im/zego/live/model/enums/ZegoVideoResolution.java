package im.zego.live.model.enums;

/**
 * Class video resolution
 * <p>
 * Description: This class contains the video resolution information. To set the video resolution, call the setVideoResolution method.
 */
public enum ZegoVideoResolution {
    VIDEO_RESOLUTION_1080P(5),
    VIDEO_RESOLUTION_720P(4),
    VIDEO_RESOLUTION_540P(3),
    VIDEO_RESOLUTION_360P(2),
    VIDEO_RESOLUTION_270P(1),
    VIDEO_RESOLUTION_180P(0);

    private final int value;

    ZegoVideoResolution(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static ZegoVideoResolution getVideoResolution(int value) {
        if (VIDEO_RESOLUTION_180P.value == value) {
            return VIDEO_RESOLUTION_180P;
        } else if (VIDEO_RESOLUTION_270P.value == value) {
            return VIDEO_RESOLUTION_270P;
        } else if (VIDEO_RESOLUTION_360P.value == value) {
            return VIDEO_RESOLUTION_360P;
        } else if (VIDEO_RESOLUTION_540P.value == value) {
            return VIDEO_RESOLUTION_540P;
        } else if (VIDEO_RESOLUTION_720P.value == value) {
            return VIDEO_RESOLUTION_720P;
        } else {
            return VIDEO_RESOLUTION_1080P.value == value ? VIDEO_RESOLUTION_1080P : null;
        }
    }
}
