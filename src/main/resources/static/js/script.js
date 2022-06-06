const CommentAPI = {
    getAll: async () => {
        return fetch(`/api.rest/comments`, { "method": "GET" });
    },
    post: async (subject, comment) => {
        return fetch(`/api.rest/comments/${subject}`, {
            method: "POST",
            body: JSON.stringify(comment),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        })
    }
}

const Comments = {
    getAll: async () => {
        try {
            const commentsPromisse = await CommentAPI.getAll();
            const comments = await commentsPromisse.json();

            return comments
        } catch (err) {
            console.error(err)
            return { error: true, details: err }
        }
    },
    post: async (subject, comment) => {
        try {
            await CommentAPI.post(subject, comment);
        }
        catch (err) {
            return { error: true, details: err }
        }
        return { status: 'success' };
    }
}

const Validator = {
    email: function (val) {
        // https://datatracker.ietf.org/doc/html/rfc2822#section-3.4.1
        const RFC2822 = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/
        return val.match(RFC2822) == null;
    }
}
/* TODO: Refatorar */
function validate(event) {
    const email = document.getElementById("email");
    const text = document.getElementById("content");
    const subject = document.getElementById("subject")

    if (Validator.email(email.value) || text.value.length == 0 || subject.value.length == 0) {
        alert("Por favor, preencha os campos com dados válidos!");
        event.preventDefault();
        return false;
    }
    return true;
}
// TODO
const PutComments = {
}

const form = document.getElementById("form")
const mail = document.getElementById("email")
const text = document.getElementById("content")
const subject = document.getElementById("subject")

function toggleForm() {
    form.classList.toggle("hidden")
    if(subject.type == "text") {
        subject.value = ''
    }
    mail.value = ''
    text.value = ''
}