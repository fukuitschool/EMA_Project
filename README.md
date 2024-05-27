○開発環境
Windows、Eclipse（Tomcat9/Java17）、JDK、DBeaver、postgreSQL

○要件定義
【システムの概要】
ユーザー登録制のイベント管理アプリ
【システムの目的】
開催予定イベントの確認・参加管理ができる。
　・生徒ユーザー：参加・キャンセル・マイページ管理ができる。
　・管理者ユーザー：イベント管理（登録・編集・削除）、ユーザー管理（登録・編集・削除）、参加・キャンセル・マイページ管理ができる。

○開発期間(全体スケジュール)　3/29〜4/16（15日間）

○アプリケーション概要
　・管理者が登録したイベントに対し、ユーザーはイベント参加・キャンセルできる環境を提供する。

○機能概要
　・ブラウザより「http://localhost:8080/EMA_Project/」で起動可能なこと。
　
 
 ・アカウント作成・登録・削除機能
　　　アカウント登録機能として、管理者は下記を登録できる。登録内容の編集・削除ができる。
　　　生徒：生徒ID(重複なし)、ログインパスワード、氏名
　　　管理者：管理者ID(重複なし)、ログインパスワード、氏名
　　　※管理者用パスワードはあらかじめ設定のパスワード（1234）で運用


　・ログイン・ログアウト機能
    認証機能を用い、生徒・管理者はシステムへログイン・ログアウトができる。
    生徒認証機能：ユーザーID、ログインパスワードを使用
    管理者認証機能：ユーザーID、ログインパスワード、管理者用パスワード（1234）を使用


　・イベント登録・編集・削除機能
　　管理者は新規イベントの登録・編集・削除処理ができる。
　　登録項目：イベントID、イベント名、イベントカテゴリー、開催日、開始時刻、開催場所


　・ダッシュボート機能
　　ユーザー(生徒・管理者)別に以下のページへアクセスできる。
　　生徒：開催中イベント申込・詳細確認、マイページ
　　管理者：イベント一覧(ページ内よりイベント参加者一覧)、ユーザー一覧、マイページ


　・生徒・管理者マイページ機能
　　生徒・管理者はマイページから、申込済イベントの確認、イベント申込のキャンセルができる。


　・入力ミスの表示機能
　　ログイン時：未入力・ログイン認証エラー、登録時：項目未入力・ユーザーID重複、イベントID重複、イベント申込時：申込重複に対し、注意喚起する。

【備考】
  ・MVCモデルを意識して開発を行うこと。
  ・開発が完了した場合は、単体テスト仕様書を使ってテストを行うこと。
  ・DAOパターンを用いること。
  ・JSTL＆EL式を用いること。

  
*今後、追加したい機能(予定)
・ユーザー画像の編集機能
・パスワードの暗号化機能
・生徒のニックネーム機能
・チャット機能
・イベントのお知らせ(新規・中止・変更)表示機能       
・イベントの日時重複の申込可否機能
・画像アップロード機能(ユーザー画像・イベント画像）


○データベース設計(SQL)　DBeverにて、以下のコードを入力し、ローカル環境で作成

　-- create database
 CREATE DATABASE ema;

 -- create table
 -- student
CREATE TABLE student
(student_id varchar(4) PRIMARY KEY,
    student_pass varchar(8) NOT NULL,
    student_name varchar(20) NOT NULL);

-- admin
CREATE TABLE admin
(admin_id varchar(4) PRIMARY KEY,
    admin_pass varchar(8) NOT NULL,
    admin_name varchar(20) NOT NULL);

-- event
CREATE TABLE event
(event_id varchar(4) PRIMARY KEY NOT NULL,
    event_name varchar(60) NOT NULL,
    event_category varchar(10) NOT NULL,
    event_date DATE NOT NULL,
    event_time varchar(10) NOT NULL,
    event_place varchar(60) NOT NULL);

-- event entry
CREATE TABLE event_entry
(entry_id SERIAL PRIMARY KEY, 
    event_id varchar(4),
    admin_id varchar(4),
    student_id varchar(4),
    status varchar(80), 
    FOREIGN KEY (event_id) REFERENCES event (event_id) ON DELETE CASCADE,
    FOREIGN KEY (admin_id) REFERENCES  admin (admin_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES student (student_id) ON DELETE CASCADE);

-- sample data
-- student table data
INSERT INTO student (student_id, student_pass, student_name) VALUES
('0001','12345678' , '太郎'),
('0002','12345678' , '次郎'),
('0003','12345678' , '三郎');

-- admin table data
INSERT INTO admin (admin_id, admin_pass, admin_name) VALUES
('0001','12345678' , '先生');

-- event table data
INSERT INTO event (event_id, event_name, event_category, event_date, event_time, event_place) VALUES
('0001', '会議', 'セミナー', '2024-05-15', '10時', '東京'),
('0002', '食事会 2', 'セミナー',  '2024-04-20', '12時', '大阪'),
('0003', 'プロジェクト', 'セミナー',  '2024-06-10', '8時', '福岡');

-- event_entry admin
INSERT INTO event_entry (event_id, admin_id, status) VALUES
('0001','0001', 'Joined'), 
('0001','0001', 'Joined'), 
('0002','0001', 'Joined'),
('0003','0001', '');

-- event_entry student
INSERT INTO event_entry (event_id, student_id, status) VALUES
('0001', '0001', 'Joined'), 
('0001', '0002', 'Joined'), 
('0002', '0002', ''),
('0003', '0002', '');    
