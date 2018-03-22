var router = new VueRouter({
        routes: [{
                path: "/",
                name: "propaganda",
                component: Propaganda
        }, {
                path: "/newadd/:id",
                name: "newadd",
                component: Newadd
        }, {
                path: "/image/:id",
                name: "image",
                component: Detailsimage
        }, {
                path: "/text/:id",
                name: "text",
                component: Detailstext
        }, {
                path: "/video/:id",
                name: "video",
                component: Detailsvideo
        }]
});