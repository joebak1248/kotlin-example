package com.example.demo.config

import org.hibernate.jpa.HibernatePersistenceProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.example.demo.repository"]
)
class DataSourceConfiguration {

    @Bean
    fun dataSource(): DataSource {
        return EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build()
    }

    @Bean
    fun entityManagerFactory(dataSource: DataSource, jpaVendorAdapter: JpaVendorAdapter): LocalContainerEntityManagerFactoryBean {
        val factory = LocalContainerEntityManagerFactoryBean()
        factory.dataSource = dataSource
        factory.jpaVendorAdapter = jpaVendorAdapter
        factory.persistenceProvider = HibernatePersistenceProvider()
        factory.setPackagesToScan("com.example.demo.domain")
        factory.afterPropertiesSet()
        return factory
    }

    @Bean
    fun jpaVendorAdapter(): JpaVendorAdapter {
        val hibernateJpaVendorAdapter = HibernateJpaVendorAdapter()
        hibernateJpaVendorAdapter.setShowSql(true)
        hibernateJpaVendorAdapter.setGenerateDdl(true)
        hibernateJpaVendorAdapter.setDatabase(Database.H2)
        return hibernateJpaVendorAdapter
    }
}