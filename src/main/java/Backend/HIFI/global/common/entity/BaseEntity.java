package Backend.HIFI.global.common.entity;

import lombok.Getter;
import javax.persistence.*;

/** 상속받으면 sofe delete 수행됩니다 .*/
@MappedSuperclass
@Getter
public class BaseEntity {
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    public void updateIsDeleted() {
        this.isDeleted = !this.isDeleted;
    }
}
