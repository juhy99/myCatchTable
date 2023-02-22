package com.catchmind.catchtable.dto.network.request;

public record ReserveRequest(
        String resMonth,
        String resDay,
        String resPerson,
        String resaBisName,
        String resTime
) {
    public static ReserveRequest of(String resMonth, String resDay, String resaBisName,String resTime) {
        return new ReserveRequest(resMonth,resDay,null, resaBisName,resTime);
    }


}
