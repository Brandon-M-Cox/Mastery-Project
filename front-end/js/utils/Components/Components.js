import Html from "../Html/html";
import Api from "../Api/Api";
// import pictureUrl from "../../../images/music.jpg";

export default () => new Components();

class Components {
    getAppContext() {
        return Html().select("#app");
    }

    getWrapperDiv() {
        return Html()
            .create("div")
            .addClass("wrapper");
    }


    renderHeaderBlock(requestData) {
        const headerBlock = Html().create('header').addClass('main-header');
        const titleBlock = Html().create('h1').addClass('main-header__title').text("Code 'N' Chill")
        const nav = this.renderNavBlock();
        headerBlock.addChild(titleBlock);
        headerBlock.addChild(nav);
        return headerBlock
    }

    renderNavBlock() {
        const navBlock = Html().create('nav').addClass('nav');
        const navList = Html().create('ul').addClass('nav__list');

        const navListItemOne = Html().create('li')
            .addClass('nav__list-item')
            .addChild(
                Html().create('a')
                    .addAttribute('href', '#')
                    .text("Home")
                    .click((event) => {
                        event.preventDefault()
                        this.renderPageHome()

                    })
            )
        const navListItemTwo = Html().create('li')
            .addClass('nav__list-item')
            .addChild(
                Html().create('a')
                    .addAttribute('href', 'allActorsTemplate.html')
                    .text("Actors")
                    .click((event) => {
                        event.preventDefault()
                        this.renderPageActors()

                    })
            )
        const navListItemThree = Html().create('li')
            .addClass('nav__list-item')
            .addChild(
                Html().create('a')
                    .addAttribute('href', 'allSeriesTemplate.html')
                    .text("Series")
                    .click((event) => {
                        event.preventDefault()
                        this.renderPageSeries()

                    })
            )
        const navListItemFour = Html().create('li')
            .addClass('nav__list-item')
            .addChild(
                Html().create('a')
                    .addAttribute('href', 'allMoviesTemplate.html')
                    .text("Movies")
                    .click((event) => {
                        event.preventDefault()
                        this.renderPageMovies()

                    })
            )
        navList.addChild(navListItemOne)
        navList.addChild(navListItemTwo)
        navList.addChild(navListItemThree)
        navList.addChild(navListItemFour)
        navBlock.addChild(navList)
        return navBlock;
    }

    renderFooter() {
        const footer = Html().create('footer').addClass('footer');
        const footerText = Html().create('small').addClass('footer__text').html('&copy; 2019');
        footer.addChild(footerText);
        return footer;
    }

    generateSingleSeriesPage(idNumber) {
        const entityType = 'series';
        this.generateSingleItemPage(entityType, idNumber)
    }

    generateSingleActorPage(idNumber) {
        const entityType = 'actors'
        this.generateSingleItemPage(entityType, idNumber);
    }

    generateSingleItemPage(entityType, idNumber) {
        let header = Html().create('h1').addClass('block__title');
        let image = Html().create('img').addClass('block__image');
        let infoP1 = Html().create('p').addClass('block__info');
        let infoP2 = Html().create('p').addClass('block__info');
        let blockList = Html().create('div').addClass('block-list');
        const api = Api().getRequest(`http://localhost:8080/api/${entityType}/${idNumber}`, (responseObject) => {
            let title;
            if (responseObject.name) {
                title = responseObject.name;
            } else {
                title = responseObject.title;
            }
            const actorImageUrl = responseObject.imageUrl;
            let infoP1Text;
            if (responseObject.dateOfBirth) {
                infoP1Text = responseObject.dateOfBirth;
            }
            if (responseObject.recordLabel) {
                infoP1Text = responseObject.recordLabel;
            }
            let infoP2Text;
            if (responseObject.homeTown) {
                infoP2Text = responseObject.homeTown;
            }
            let itemList;
            if (responseObject.series) {
                itemList = responseObject.series;
            }
            if (responseObject.movies) {
                itemList = responseObject.movies
            }

            header = header.text(title);
            image = image.addAttribute("src", actorImageUrl);
            infoP1 = infoP1.text(infoP1Text);
            infoP2 = infoP2.text(infoP2Text);
            itemList.forEach(item => {
                let id;
                id = item.id;
                let name;
                name = item.name;
                if (item.title) {
                    name = item.title;
                }
                let imageUrl;
                imageUrl = item.imageUrl;
                if (!imageUrl) {
                    imageUrl = pictureUrl;
                }
                let itemLink;
                itemLink = "#";
                if (item.link) {
                    itemLink = item.link;
                }
                const article = Html()
                    .create("article")
                    .addClass("card");
                const itemAnchorLink = Html()
                    .create("a")
                    .addClass("card__anchor")
                    .addAttribute("href", itemLink)
                    .addAttribute("target", "_blank")
                    .click(event => {
                        console.log("test");
                        if (item.series) {
                            event.preventDefault();
                            this.generateSingleActorPage(id);
                        }
                        if (item.movies) {
                            event.preventDefault();
                            this.generateSingleSeriesPage(id);
                        }
                    });
                const img = Html()
                    .create("img")
                    .addClass("card__image")
                    .addAttribute("src", imageUrl)
                    .addAttribute("alt", "alt picture");
                const section = Html()
                    .create("section")
                    .addClass("card__item");
                const sectionHeader = Html()
                    .create("h2")
                    .addClass("card__item--text")
                    .text(name);
                section.addChild(sectionHeader);
                itemAnchorLink.addChild(img);
                itemAnchorLink.addChild(section);
                article.addChild(itemAnchorLink);
                blockList.addChild(article);
            });
        }
        );
        const container = this.getWrapperDiv().select(".container");
        container.replace(header);
        container.addChild(image);
        container.addChild(infoP1);
        container.addChild(infoP2);
        container.addChild(blockList);
    }

    generateActorList() {
        return this.generateContentBlockListFromApi("actors");
    }
    generateSeriesList() {
        return this.generateContentBlockListFromApi("series");
    }

    generateMoviesList() {
        return this.generateContentBlockListFromApi("movies");
    }

    renderPageActors() {
        this.replaceContainerContent("actors");
    }

    renderPageSeries() {
        this.replaceContainerContent("series");
    }

    renderPageMovies() {
        this.replaceContainerContent("movies");
    }

    renderPageHome() {
        this.replaceContainerContent();
    }

    replaceContainerContent(requestedContent) {
        const container = this.getWrapperDiv().select(".container");
        container.replace(this.generateContentHeader(requestedContent));
        container.addChild(this.generateContentBlockListFromApi(requestedContent));
    }

    generateContentHeader(requestedContent) {
        let contentHeaderText;
        contentHeaderText = "";
        if (requestedContent) {
            contentHeaderText =
                requestedContent.charAt(0).toUpperCase() + requestedContent.slice(1);
        }
        return Html()
            .create("h1")
            .addClass("block__title")
            .text(contentHeaderText);
    }

    renderWholePage(requestedContent) {
        const blockHeader = this.generateContentHeader(requestedContent);
        const blockList = this.generateContentBlockListFromApi(requestedContent);

        const app = this.getAppContext();
        const wrapperDiv = this.getWrapperDiv();
        const mainHeader = this.renderHeaderBlock();
        const mainFooter = this.renderFooter();
        const container = Html()
            .create("div")
            .addClass("container");
        const block = Html()
            .create("section")
            .addClass("block");

        container.addChild(blockHeader);
        container.addChild(blockList);
        wrapperDiv.addChild(mainHeader);
        wrapperDiv.addChild(container);
        wrapperDiv.addChild(mainFooter);
        app.addChild(wrapperDiv);
        return app;
    }
    generateContentBlockListFromApi(requestedContent) {
        let blockList = Html()
            .create("div")
            .addClass("block-list");
        if (!requestedContent) {
            return blockList;
        }
        const contentLink = `http://localhost:8080/api/${requestedContent}`;
        Api().getRequest(contentLink, responseCollection => {
            responseCollection.forEach(item => {
                let id;
                id = item.id;
                let name;
                name = item.name;
                if (item.title) {
                    name = item.title;
                }
                let imageUrl;
                imageUrl = item.imageUrl;
                if (!imageUrl) {
                    imageUrl = pictureUrl;
                }

                let itemLink;
                itemLink = "#";
                if (item.link) {
                    itemLink = item.link;
                }
                const article = Html()
                    .create("article")
                    .addClass("card");
                const itemAnchorLink = Html()
                    .create("a")
                    .addClass("card__anchor")
                    .addAttribute("href", itemLink)
                    .addAttribute("target", "_blank")
                    .click(event => {
                        if (item.series) {
                            event.preventDefault();
                            this.generateSingleActorPage(id);
                        }
                        if (item.movies) {
                            event.preventDefault();
                            this.generateSingleSeriesPage(id);
                        }
                    });
                const img = Html()
                    .create("img")
                    .addClass("card__image")
                    .addAttribute("src", imageUrl)
                    .addAttribute("alt", "alt picture");
                const section = Html()
                    .create("section")
                    .addClass("card__item");
                const sectionHeader = Html()
                    .create("h2")
                    .addClass("card__item--text")
                    .text(name);
                section.addChild(sectionHeader);
                itemAnchorLink.addChild(img);
                itemAnchorLink.addChild(section);
                article.addChild(itemAnchorLink);
                blockList.addChild(article);
            });
        });
        return blockList;
    }
}
