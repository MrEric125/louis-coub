1.生成文档
    clone : docker run --name repo alpine/git clone https://github.com/docker/getting-started.ght
            docker cp repo:/git/getting-started/ .
    build :cd getting-started docker build -t docker101tutorial .
    run: docker run -d -p 80:80 --name docker-tutorial docker101tutorial
    查看文档： localhost