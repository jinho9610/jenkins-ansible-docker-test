const express = require('express');

const app = express();

users = [
    {
        'id': '1',
        'last-name': 'Kim',
        'age': '22'
    },
    {
        'id': '2',
        'last-name': 'Lee',
        'age': '17'
    }
]

app.get('/', (req, res) => {
    return res.send('Ready')
})

app.get('/users', (req, res) => {
    return res.send({
        'users': users
    })
})

app.get('/users/:id', (req, res) => {
    const id = req.params.id

    for (let i = 0; i < users.length; i++) {
        if (users[i].id == id) {
            return res.send(users[i])
        }
    }
    return res.status(400).send('There\' is no person who has that id');
})

app.get('/users/:id/:age', (req, res) => {
    const id = req.params.id
    
    for (let i = 0; i < users.length; i++) {
        if (users[i].id == id) {
            return res.send({
                'age': users[i].age
            })
        }
    }
})

app.listen(5555, () => {
    
})