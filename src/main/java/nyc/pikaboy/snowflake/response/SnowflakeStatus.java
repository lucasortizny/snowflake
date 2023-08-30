package nyc.pikaboy.snowflake.response;

import lombok.Data;


public enum SnowflakeStatus {
    APPROVED("Approved"), DENIED("Denied"), UNAVAILABLE("Unavailable");
    final String status;

    SnowflakeStatus(String status){
        this.status = status;
    }

}
