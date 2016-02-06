package com.pantau.core

import grails.plugin.cache.CacheEvict

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class ComodityController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Comodity.list(params), model:[comodityInstanceCount: Comodity.count()]
    }

    def show(Comodity comodityInstance) {
        respond comodityInstance
    }

    def create() {
        respond new Comodity(params)
    }

    @Transactional
    @CacheEvict(value = ['comodity', 'comodityall'], allEntries = true)
    def save(Comodity comodityInstance) {
        if (comodityInstance == null) {
            notFound()
            return
        }

        if (comodityInstance.hasErrors()) {
            respond comodityInstance.errors, view:'create'
            return
        }

        comodityInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'comodity.label', default: 'Comodity'), comodityInstance.id])
                redirect comodityInstance
            }
            '*' { respond comodityInstance, [status: CREATED] }
        }
    }

    def edit(Comodity comodityInstance) {
        respond comodityInstance
    }

    @Transactional
    @CacheEvict(value = ['comodity', 'comodityall'], allEntries = true)
    def update(Comodity comodityInstance) {
        if (comodityInstance == null) {
            notFound()
            return
        }

        if (comodityInstance.hasErrors()) {
            respond comodityInstance.errors, view:'edit'
            return
        }

        comodityInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Comodity.label', default: 'Comodity'), comodityInstance.id])
                redirect comodityInstance
            }
            '*'{ respond comodityInstance, [status: OK] }
        }
    }

    @Transactional
    @CacheEvict(value = ['comodity', 'comodityall'], allEntries = true)
    def delete(Comodity comodityInstance) {

        if (comodityInstance == null) {
            notFound()
            return
        }

        comodityInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Comodity.label', default: 'Comodity'), comodityInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'comodity.label', default: 'Comodity'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
