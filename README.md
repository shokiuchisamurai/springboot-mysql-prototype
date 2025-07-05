# 環境構築手順

## 概要
以下のような構成になっています。

- `環境`: Docker
- `サーバー`: Spring Boot
- `DB`: MySQL
- `DBクライアント`: phpMyAdmin
- `エディタ`: VSCode(Remote - Containers を使用)

サンプル機能として、簡単なデータベーステーブルにアクセスする処理を実装しています。（http://localhost:8080/showaccount）

データベースを編集するためにDBクライアントもDockerで用意しています。（http://localhost:4200）

## アプリケーションの起動に必要なもの
- Docker Desktop
  適宜自分のOSに合った物をインストール
  > https://www.docker.com/ja-jp/get-started/
  
  - Windowsの場合AMD64でOK
- VSCode
  適宜自分のOSに合った物をインストール
  > https://azure.microsoft.com/ja-jp/products/visual-studio-code

## 起動手順

VSCodeに`./vscode/setting.json`に記載のある拡張機能を入れておいてください。
> https://marketplace.visualstudio.com/items?itemName=ms-azuretools.vscode-docker
> 
> https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers
> 
> https://marketplace.visualstudio.com/items?itemName=jakebathman.mysql-syntax

自身のPCの任意のフォルダ（作業するフォルダ）にこのGithubをダウンロードしておいてください
> https://github.com/shokiuchisamurai/springboot-mysql-prototype

### 1.コンテナ起動
- VSCodeでダウンロードしたフォルダを開く。
- ターミナルのタブから新しいターミナルを開く。
- ターミナルで`springboot-mysql-prototype`フォルダに移動。(pwdコマンドで確認)
- docker-compose.yml は`./docker`にあります。docker 関連のコマンドはここで実行してください。
まずは docker でコンテナを起動します。

```bash:
$ cd docker
$ docker-compose up -d

# 以下の表示でコンテナ起動完了
Creating mysql     ... done
Creating dbclient  ... done
Creating java  ... done
```

### 2.phpMyAdmin の起動確認
phpMyAdmin は 4200 番ポートで起動しています。
- `http://localhost:4200/`にアクセスして表示を確認。
- `sample_schema`という DB に`account`テーブルを確認（初期データの内容は`./docker/mysql/sql_init` 以下にあるファイル内のクエリに基づきます。）

### 3.Remote - Containers でコンテナ内の環境を開く
- VSCode の画面左下に、`><`← のように括弧が合わさった緑色のボタンがあるのでそれを押してください。
- 選択肢が出るので、`Attach to Running Container（実行中のコンテナにアタッチ）`を選択します。
- 選択肢が続くので`/java`を選択してください。これで java コンテナの環境が展開されます。

### 4.ビルドと起動
- 展開したVScodeのトップから開くを選択して、`/app`フォルダを開いてください。
- ターミナルのタブから新しいターミナルを開く。
- 上記手順の続きで、現在 java コンテナの`/app`ディレクトリにいる状態です。(pwdコマンドで確認)
まずはビルド→実行します。

```
$ sh gradlew build
```

通常実行する場合は、`./build/libs/sample-0.0.1-SNAPSHOT.jar`を実行してください。

```
$ java -jar build/libs/sample-0.0.1-SNAPSHOT.jar
```
> http://localhost:8080/showaccount

上記がブラウザで表示されば動作確認完了です。

## デバッグについて
デバッグの際は、gradle を使って起動します。

```
$ sh gradlew bootRun
```

デバッガがアタッチされるまで起動を待機する設定になっているので、デバッガを起動します。

デバッグビューを開いて、`java(Attach)`をスタートさせます。これでアプリケーションがデバッグモードで
立ち上がります。
