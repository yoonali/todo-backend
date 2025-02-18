package peep.com.todo_backend.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import peep.com.todo_backend.user.domain.QUser;

@RequiredArgsConstructor
public class UserJpaCustomRepositoryImpl implements UserJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * email 기반 데이터 존재 여부 조회
     * @param email
     * @return
     */
    @Override
    public boolean existByUserEmail(String email) {
        QUser qUser = QUser.user;

        Integer fetchOne = queryFactory
                .selectOne()
                .from(qUser)
                .where(qUser.email.eq(email))
                .fetchFirst();

        return fetchOne != null;

    }
}
