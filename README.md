# Java + Spring Frameworkを使用した技術書レビュー投稿アプリ
読み終わった**IT技術書のレビューを投稿**し、情報を共有してユーザー同士で**IT技術の勉強教材の選別に役立てる**ことを目的としたアプリです。  

![home](https://github.com/user-attachments/assets/ab0c7039-94b4-40bf-8bb5-fa69ceb59827)

## 主な機能
  ### ユーザー新規登録機能
  <details><summary>ユーザーの新規登録機能</summary>
  
  #### ユーザーの新規登録が可能  
  ![image](https://github.com/user-attachments/assets/aa72eb3d-7dee-4b8c-9b28-c506e50e62e7)
   
   ---
  ![image](https://github.com/user-attachments/assets/7eba9a8c-0117-4659-a7f8-89bc54da1498)
  </details>
  
  ### ログイン機能
  <details><summary>ログイン機能</summary>

  #### ユーザーのログインが可能
  ![image](https://github.com/user-attachments/assets/28dc585d-8828-4df3-811c-f12a17ed5548)
  
  ---
 ![image](https://github.com/user-attachments/assets/e83de452-cfe3-45db-a1a6-372b2f125f13)
  
  </details>
  
  ### 投稿一覧表示
  <details><summary>投稿一覧表示</summary>

  #### 一覧にて各ユーザーが投稿したIT技術書レビューを確認可能
 ![postList](https://github.com/user-attachments/assets/c52e3f67-2ce5-47a7-a075-6ccab27ca4dc)
  </details>

  ### CRUD機能
  <details><summary>投稿新規作成</summary>

  #### 投稿を新規作成することが可能
  ![postCreate](https://github.com/user-attachments/assets/395a450f-df0b-43ab-99b1-7e38b36d963a)  
  
  ---
  ![postCreate_success_](https://github.com/user-attachments/assets/a30abdcd-7383-4c80-9490-3c86d7578e12)

  </details>
  <details><summary>投稿詳細表示</summary>

  #### 投稿の詳細を確認することが可能
  - **ログインユーザー**が作成した投稿の詳細ページ
　![postDetail_login_user](https://github.com/user-attachments/assets/912ca3d2-78c2-44c4-8de6-42ff527d8708)
  ---
  - **他のユーザー**が作成した投稿の詳細ページ
  ![postDetail_another_user](https://github.com/user-attachments/assets/1ca9dad9-c3a1-41ba-bc00-fed535f6bf1f)

  </details>
  <details><summary>投稿編集機能</summary>

  #### 投稿を編集することが可能
  - 編集フォームにて編集後、更新ボタンを押下
   ![postEdit](https://github.com/user-attachments/assets/6c170549-c932-4bbd-872a-705027ae70e0)
  ---
  - 投稿編集後、投稿一覧
   ![postEdit_success](https://github.com/user-attachments/assets/c3524566-91cc-47ab-a159-65cd3d96606b)
  ---
  - 投稿編集後、投稿詳細
  ![edit_after_postDetail](https://github.com/user-attachments/assets/d41f7147-5fe8-441f-befb-76b77139028c)

  </details>
  <details><summary>投稿削除機能</summary>

  #### 投稿を削除することが可能  
  ![postDelete_confirm](https://github.com/user-attachments/assets/e12ae4cc-c6b7-4eb5-b334-5e181d49f1db)　
 
  ---
  ![postDelete_success](https://github.com/user-attachments/assets/b40c3339-bc2f-4644-a6a5-9b7e55d2e8d7)

  </details>


  ### ユーザーの情報編集  
  <details><summary>ユーザー情報の編集が可能(名前、メールアドレス、プロフィール画像)</summary>  
    
 ![image](https://github.com/user-attachments/assets/ef37c491-b360-4725-a9d3-6792d8d53566)
  
  --- 
  ![userEdit_success](https://github.com/user-attachments/assets/0cab8a24-5a22-4199-a81b-545cfed3344e)  
  
  ---
  ![user_picture_set_postList](https://github.com/user-attachments/assets/70ff8454-e601-4e99-8713-b5e5231bda77)
  
  </details>
  
  ### ログアウト機能
   <details><summary>ログアウトボタンを押下するとログアウトしてログイン画面に遷移</summary>

  ![logout_success](https://github.com/user-attachments/assets/48bcb7f8-031c-4c23-a54d-5e722a96d124)
  </details>

  ### バリデーション
  <details><summary>各フォームにてバリデーションを処理を実装（バックエンド側）</summary>
    
  ![image](https://github.com/user-attachments/assets/822badb1-3459-4f15-a493-639ed1a7879a)

  </details>
  
## ER図
<img src="https://github.com/user-attachments/assets/0a77af8c-d2fb-4366-b997-9ff72193fd42" alt="ER図" width="600">

