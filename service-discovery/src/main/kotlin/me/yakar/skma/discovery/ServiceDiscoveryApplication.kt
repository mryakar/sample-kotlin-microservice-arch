package me.yakar.skma.discovery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServiceDiscoveryApplication

fun main(args: Array<String>) {
	runApplication<ServiceDiscoveryApplication>(*args)
}
