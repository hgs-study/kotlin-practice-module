package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.util.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookService(
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @Transactional
    fun saveBook(request: BookRequest){
        val newBook = Book(request.name)
        bookRepository.save(newBook)
    }

    @Transactional
    fun loanBook(request: BookLoanRequest){
        val findBook = bookRepository.findByName(request.bookName) ?: fail()
        if(userLoanHistoryRepository.findByBookNameAndIsReturn(request.bookName, false) != null){
            throw IllegalArgumentException("진작 대출되어 있는 책입니다")
        }

        val findUser = userRepository.findByName(request.userName) ?: fail()
        findUser.loanBook(findBook)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest){
        val findUser = userRepository.findByName(request.userName) ?: fail()
        findUser.returnBook(request.bookName)
    }
}