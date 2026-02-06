package com.amirbahadoramiri.androidlearning.views.maptesting;

public class MapirStyle {
    /**
     * Map.ir basic map style
     */
    @Deprecated(
            /**
             * As of 4.1.0,
            use {@link #VERNA} style instead(same style)
             *
            */
    )
    public static final String MAIN_MOBILE_VECTOR_STYLE =
            "https://map.ir/vector/styles/main/main_mobile_style.json";
    public static final String VERNA =
            "https://map.ir/vector/styles/main/main_mobile_style.json";
    /**
     * Map.ir dark map style
     */
    public static final String CARMANIA =
            "https://map.ir/vector/styles/main/mapir-style-dark.json";
    /**
     * Map.ir basic map style with minimum POIs
     */
    public static final String ISATIS =
            "https://map.ir/vector/styles/main/mapir-style-min-poi.json";
    /**
     * Map.ir basic map style with no POIs
     */
    public static final String LIGHT =
            "https://map.ir/vector/styles/main/mapir-xyz-style-min-poi.json";
    /**
     * Map.ir basic map style with world tiles
     */
    public static final String WORLD =
            "https://map.ir/vector/styles/main/mapir-xyz-style.json";
    // The HYRCANIA constant is commented out in the original Kotlin code, so it's not included here.
}