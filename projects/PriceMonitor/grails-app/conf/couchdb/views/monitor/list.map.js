function(doc) {
    if (doc.type == 'monitor') {
        emit(doc._id, doc);
    }
}