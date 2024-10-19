INSERT INTO users (id, username, password)
VALUES ('1e72dd40-1c19-42e1-9239-0a1b8495a1ff', 'user1', 'password123'),
       ('2d849fa2-2134-47ba-9ba7-7c18f8b37c33', 'user2', 'password456'),
       ('5b167438-77df-4658-b71a-78816be54f80', 'user3', 'password789');

INSERT INTO posts (id, title, content, slug, user_id)
VALUES ('a512b7a0-9f4c-49f8-85b4-913c29e1e7cb', 'First Post',
        'This is the content of the first post.', 'first-post',
        '1e72dd40-1c19-42e1-9239-0a1b8495a1ff'),
       ('b78d0934-99a1-4425-b1fa-dcb84b109b1a', 'Second Post',
        'This is the content of the second post.', 'second-post',
        '2d849fa2-2134-47ba-9ba7-7c18f8b37c33'),
       ('c4e7bdf3-1442-4df7-9fd0-fb5e98c4bb7a', 'Third Post',
        'This is the content of the third post.', 'third-post',
        '5b167438-77df-4658-b71a-78816be54f80');
