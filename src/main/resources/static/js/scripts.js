function copyLink(id, button) {
    var currentLink = window.location.href;
    currentLink = currentLink.replace("all", "");
    const modifiedLink = currentLink + "id/" + id;

    var textarea = document.createElement('textarea');
    textarea.value = modifiedLink;
    document.body.appendChild(textarea);
    textarea.select();

    try {
        document.execCommand('copy');
        var buttonRect = button.getBoundingClientRect();
        showMessage('Link copied to clipboard!', buttonRect);
    } catch (err) {
        console.error('Unable to copy to clipboard:', err);
    } finally {
        document.body.removeChild(textarea);
    }

    console.log('Modified Link:', modifiedLink);
}

function showMessage(message, buttonRect) {
    var messageElement = document.createElement('div');
    messageElement.innerText = message;
    messageElement.style.position = 'fixed';
    messageElement.style.top = buttonRect.bottom + 'px';
    messageElement.style.left = buttonRect.left + 'px';
    messageElement.style.backgroundColor = '#4CAF50';
    messageElement.style.color = '#fff';
    messageElement.style.padding = '10px';
    messageElement.style.borderRadius = '5px';
    messageElement.style.opacity = '1';
    messageElement.style.transition = 'opacity 1.5s ease-out';

    document.body.appendChild(messageElement);

    setTimeout(function () {
        messageElement.style.opacity = '0';
    }, 1000);
}