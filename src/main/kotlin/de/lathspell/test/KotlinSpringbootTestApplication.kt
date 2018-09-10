package de.lathspell.test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@SpringBootApplication(scanBasePackageClasses = [Application::class], excludeName = ["*"])
@ComponentScan(lazyInit = true, basePackages = ["de.lathspell.test"], includeFilters = [])
class Application {

    @Configuration
    @Import(HttpMessageConvertersAutoConfiguration::class)
    @ComponentScan(lazyInit = true, basePackages = ["de.lathspell.test"], includeFilters = [])
    internal class LocalConfig

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}