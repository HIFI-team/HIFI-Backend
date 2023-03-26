package Backend.HIFI.global.common.entity;

import lombok.Getter;
import javax.persistence.*;

/** 상속받으면 생성시간 업데이트시간
 * 생성한 사람, 업데이트한 사람 필드 자동으로 만들어주는 엔티티입니다
 * jpa의 audit(감시) 기능을 사용합니다 */
@MappedSuperclass
@Getter
public class BaseEntity {
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    public void updateIsDeleted() {
        this.isDeleted = !this.isDeleted;
    }
}
