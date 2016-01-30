package com.pantau.core

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import java.text.DecimalFormat

@Secured(['permitAll'])
class PageController {

    def index() {
        return [ranks: topPosters()]
    }

    def comodityInputGeoJSON() {
        def pattern = "##,###.##"
        def moneyform = new DecimalFormat(pattern)

        def results = [:]
        results['type'] = 'FeatureCollection'
        def items = []
        ComodityInput.list(params).each {
            def item = [:]
            item['type'] = 'Feature'
            item['properties'] = [
                    'comodityType': it.comodityName?.comodityType?.name,
                    'comodityName': it.comodityName?.name,
                    //'username': it.user?.username,
                    'price': moneyform.format(it.price),
                   // 'phone': it.user?.nohp,
                    'amount': it.amount
            ]
            item['geometry'] = ['type': "Point", 'coordinates': [it?.lng, it?.lat]]
            items.push(item)
        }

        results['features'] = items
        render results as JSON
    }

    def topPosters() {
        def res = []
        def excludes = "'admin', 'ivan.sugiarto@gmail.com', 'wid.pangestu@gmail.com'"
        def query = """
            select count(c.id) as posts, u.username as username
            from ComodityInput c join c.user u
            where u.username not in ( ${excludes} )
            and u.username like '%@%'
            group by u.username
            order by posts desc
        """
        def result = ComodityInput.executeQuery(query, [max: 10])
        result?.each {
            res << [posts: it[0], username: it[1]]
        }
        return res
    }
}
