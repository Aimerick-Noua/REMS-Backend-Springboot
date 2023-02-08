package com.app.res.mailling;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<MailClass,Long> {
}
