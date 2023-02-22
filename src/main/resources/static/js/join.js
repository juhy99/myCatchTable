'use strict';

const form = document.querySelector('#join-form');                      // form__wrap
const checkAll = document.querySelector('.terms__check__all input');
const checkBoxes = document.querySelectorAll('.input__check input');
const submitButton = document.querySelector('#submit-button');          // button

const agreements = {
  RequiredSignupAge: false,                 // termsOfService
  RequiredTermsCondition: false,            // privacyPolicy
  RequiredTermsOfPrivacy: false,            // allowPromotions
  RequiredTermsOfPrivacyPersonal: false,
  OptionalTermsOfPrivacy: false
  // SignupEventAll: false
};

// form.addEventListener('submit', (e) => e.preventDefault()); // 새로고침(submit) 되는 것 막음

checkBoxes.forEach((item) => item.addEventListener('input', toggleCheckbox));

function toggleCheckbox(e) {
  const { checked, id } = e.target;  
  agreements[id] = checked;
  this.parentNode.classList.toggle('active');
  checkAllStatus();
  toggleSubmitButton();
}

function checkAllStatus() {
  const { RequiredSignupAge, RequiredTermsCondition, RequiredTermsOfPrivacy, RequiredTermsOfPrivacyPersonal, OptionalTermsOfPrivacy } = agreements;
          // termsOfService, privacyPolicy, allowPromotions
  if ( RequiredSignupAge && RequiredTermsCondition && RequiredTermsOfPrivacy && RequiredTermsOfPrivacyPersonal && OptionalTermsOfPrivacy ) {
                       // termsOfService && privacyPolicy && allowPromotions
    checkAll.checked = true;
  } else {
    checkAll.checked = false;
  }
}

function toggleSubmitButton() {
  const { RequiredSignupAge, RequiredTermsCondition, RequiredTermsOfPrivacy, RequiredTermsOfPrivacyPersonal } = agreements;       // termsOfService, privacyPolicy
  if ( RequiredSignupAge && RequiredTermsCondition && RequiredTermsOfPrivacy && RequiredTermsOfPrivacyPersonal ) {                // termsOfService && privacyPolicy
    submitButton.disabled = false;
  } else {
    submitButton.disabled = true;
  }
}

checkAll.addEventListener('click', (e) => {
  const { checked } = e.target;
  if (checked) {
    checkBoxes.forEach((item) => {
      item.checked = true;
      agreements[item.id] = true;
      item.parentNode.classList.add('active');
    });
  } else {
    checkBoxes.forEach((item) => {
      item.checked = false;
      agreements[item.id] = false;
      item.parentNode.classList.remove('active');
    });
  }
  toggleSubmitButton();
});





