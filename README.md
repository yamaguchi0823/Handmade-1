# Handmade-1

ハンドメイド作品の在庫・販売管理を行うWebアプリケーションです。

## 概要
ハンドメイド作家が、作品ごとの在庫数や販売履歴を管理することを目的とした
在庫・販売管理システムです。

## 使用技術
- Java 21
- Spring Boot
- Thymeleaf
- MyBatis
- MySQL

## 機能一覧
- 商品管理（登録・一覧・詳細・編集・削除）
- 商品バリエーション管理（色・サイズ・在庫）
- 販売登録（在庫自動減算）
- 在庫一覧表示

## DB構築方法
MySQLで `sql/01_create_tables.sql` を実行してください。

## 環境変数
以下の環境変数を設定してください。

- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

## 起動方法
1. MySQLを起動
2. 環境変数を設定
3. Spring Boot を起動
